package com.bottle.team.lucene;

import com.bottle.team.lucene.annotations.Indexed;
import com.bottle.team.lucene.annotations.IndexedEmbedded;
import com.bottle.team.lucene.helper.FieldDescriptor;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;
import org.apache.lucene.document.*;
import org.apache.lucene.util.BytesRef;
import org.springframework.data.annotation.Id;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.*;

/**
 * Created by AKuzmanoski on 12/01/2017.
 *
 * @author AKuzmanoski
 * @version 1.0
 * @since 12/01/2017
 */
public class LuceneUtils {
    public static boolean isIndexed(Class c) {
        if (c == null)
            return false;
        if (c.isAnnotationPresent(Indexed.class))
            return true;
        if (isIndexed(c.getSuperclass()))
            return true;
        for (Class i : c.getInterfaces())
            if (isIndexed(i)) return true;
        return false;
    }

    public static LinkedList<Field> getFields(Class c, Class<? extends Annotation> a) {
        LinkedList<Field> list = new LinkedList<>();
        if (c == null)
            return list;
        Field[] fields = c.getDeclaredFields();
        for (Field f : fields) {
            if (f.isAnnotationPresent(a))
                list.add(f);
        }
        list.addAll(getFields(c.getSuperclass(), a));
        return list;
    }

    private static Set<Class> getTypes(Class type) {
        Set<Class> types = new HashSet<>();
        if (type == null)
            return types;
        types.add(type);
        types.addAll(getTypes(type.getSuperclass()));
        for (Class iType : type.getInterfaces()) {
            types.addAll(getTypes(iType));
        }
        return types;
    }

    private static void setTypes(Document document, Object object, String prefix) {
        for (Class type : getTypes(object.getClass()))
            document.add(new TextField(prefix + "{{type}}", type.getName(), org.apache.lucene.document.Field.Store.YES));
    }

    public static void setId(Document document, Object object) {
        Field idField = getFields(object.getClass(), Id.class).getFirst();
        idField.setAccessible(true);
        Long id = -1L;
        try {
            id = (Long) idField.get(object);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        document.add(new StringField("{{id}}", id.toString(), org.apache.lucene.document.Field.Store.YES));
    }

    public static Document getDocument(Object object) {
        Document document = new Document();
        setFieldsToDocument(object, document, "", 1.0f);

        setId(document, object);

        return document;
    }

    public static void setFieldsToDocument(Object object, Document document, String prefix, float baseBoost) {
        setTypes(document, object, prefix);
        Class c = object.getClass();
        List<Field> fields = getFields(c, com.bottle.team.lucene.annotations.Field.class);
        for (Field field : fields) {
            try {
                setFieldToDocument(document, field, object, prefix, baseBoost);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        fields = getFields(c, IndexedEmbedded.class);
        for (Field field : fields) {
            Object embedded = null;
            field.setAccessible(true);
            try {
                embedded = field.get(object);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
            if (embedded == null)
                continue;
            IndexedEmbedded indexedEmbedded = field.getAnnotation(IndexedEmbedded.class);
            setFieldsToDocument(embedded, document, prefix + field.getName() + ".", baseBoost * indexedEmbedded.boost().value());
        }
    }

    public static void setFieldToDocument(Document document, Field field, Object object, String prefix, float baseBoost) throws IllegalAccessException {
        FieldDescriptor fieldDescriptor = new FieldDescriptor(field, prefix, baseBoost);
        field.setAccessible(true);
        if (field.isSynthetic()) {
            if (fieldDescriptor.getFieldClass().equals(int.class)) {
                Integer value = field.getInt(object);
                if (fieldDescriptor.isIndexed())
                    document.add(new IntPoint(fieldDescriptor.getName(), value));
                if (fieldDescriptor.isStored())
                    document.add(new StoredField(fieldDescriptor.getName(), value));
            } else if (fieldDescriptor.getFieldClass().equals(long.class)) {
                Long value = field.getLong(object);
                if (fieldDescriptor.isIndexed())
                    document.add(new LongPoint(fieldDescriptor.getName(), value));
                if (fieldDescriptor.isStored())
                    document.add(new StoredField(fieldDescriptor.getName(), value));
            } else if (fieldDescriptor.getFieldClass().equals(float.class)) {
                Float value = field.getFloat(object);
                if (fieldDescriptor.isIndexed())
                    document.add(new FloatPoint(fieldDescriptor.getName(), value));
                if (fieldDescriptor.isStored())
                    document.add(new StoredField(fieldDescriptor.getName(), value));
            } else if (fieldDescriptor.getFieldClass().equals(short.class)) {
                Short value = field.getShort(object);
                if (fieldDescriptor.isIndexed())
                    document.add(new IntPoint(fieldDescriptor.getName(), value));
                if (fieldDescriptor.isStored())
                    document.add(new StoredField(fieldDescriptor.getName(), value));
            } else if (fieldDescriptor.getFieldClass().equals(byte.class)) {
                Byte value = field.getByte(object);
                if (fieldDescriptor.isIndexed())
                    document.add(new IntPoint(fieldDescriptor.getName(), value));
                if (fieldDescriptor.isStored())
                    document.add(new StoredField(fieldDescriptor.getName(), value));
            } else if (fieldDescriptor.getFieldClass().equals(char.class)) {
                Character value = field.getChar(object);
                if (fieldDescriptor.isSorted())
                    document.add(new SortedDocValuesField(fieldDescriptor.getName(), new BytesRef(value)));
                if (fieldDescriptor.isIndexed())
                    document.add(new StringField(fieldDescriptor.getName(), value.toString(), fieldDescriptor.getStore()));
            } else if (fieldDescriptor.getFieldClass().equals(double.class)) {
                Double value = field.getDouble(object);
                if (fieldDescriptor.isIndexed())
                    document.add(new DoublePoint(fieldDescriptor.getName(), value));
                if (fieldDescriptor.isStored())
                    document.add(new StoredField(fieldDescriptor.getName(), value));
            } else if (fieldDescriptor.getFieldClass().equals(boolean.class)) {
                Boolean value = field.getBoolean(object);
                document.add(new StringField(fieldDescriptor.getName(), value.toString(), fieldDescriptor.getStore()));
            }
        } else {
            Object value = field.get(object);
            if (value == null)
                return;
            if (fieldDescriptor.getFieldClass().equals(Date.class)) {
                Date date = (Date) value;
                String data = DateTools.dateToString(date, DateTools.Resolution.MILLISECOND);
                if (fieldDescriptor.isSorted())
                    document.add(new SortedDocValuesField(fieldDescriptor.getName(), new BytesRef(data.getBytes())));
                if (fieldDescriptor.isIndexed())
                    document.add(new StringField(fieldDescriptor.getName(), data, fieldDescriptor.getStore()));
            } else if (fieldDescriptor.getFieldClass().equals(String.class)) {
                String data = (String) value;
                org.apache.lucene.document.Field newField = new TextField(fieldDescriptor.getName(), data, fieldDescriptor.getStore());
                newField.setBoost(fieldDescriptor.getBoost());
                if (fieldDescriptor.isIndexed())
                    document.add(newField);
                /*else if (fieldDescriptor.isStored())
                    document.add(new StoredField(fieldDescriptor.getName(), CompressionTools.compressString(data)));*/
            } else if (fieldDescriptor.getFieldClass().equals(Boolean.class)) {
                Boolean data = (Boolean) value;
                document.add(new StringField(fieldDescriptor.getName(), data.toString(), fieldDescriptor.getStore()));
            } else if (fieldDescriptor.getFieldClass().equals(Character.class)) {
                Character data = (Character) value;
                if (fieldDescriptor.isIndexed())
                    document.add(new StringField(fieldDescriptor.getName(), data.toString(), fieldDescriptor.getStore()));
                if (fieldDescriptor.isSorted())
                    document.add(new SortedDocValuesField(fieldDescriptor.getName(), new BytesRef(data.toString().getBytes())));
            } else if (fieldDescriptor.getFieldClass().equals(Integer.class)) {
                Integer data = (Integer) value;
                if (fieldDescriptor.isIndexed())
                    document.add(new IntPoint(fieldDescriptor.getName(), data));
                if (fieldDescriptor.isStored())
                    document.add(new StoredField(fieldDescriptor.getName(), data));
            } else if (fieldDescriptor.getFieldClass().equals(Long.class)) {
                Long data = (Long) value;
                if (fieldDescriptor.isIndexed())
                    document.add(new LongPoint(fieldDescriptor.getName(), data));
                if (fieldDescriptor.isStored())
                    document.add(new StoredField(fieldDescriptor.getName(), data));
            } else if (fieldDescriptor.getFieldClass().equals(Short.class)) {
                Integer data = (Integer) value;
                if (fieldDescriptor.isIndexed())
                    document.add(new IntPoint(fieldDescriptor.getName(), data));
                if (fieldDescriptor.isStored())
                    document.add(new StoredField(fieldDescriptor.getName(), data));
            } else if (fieldDescriptor.getFieldClass().equals(Float.class)) {
                Float data = (Float) value;
                if (fieldDescriptor.isIndexed())
                    document.add(new FloatPoint(fieldDescriptor.getName(), data));
                if (fieldDescriptor.isStored())
                    document.add(new StoredField(fieldDescriptor.getName(), data));
            } else if (fieldDescriptor.getFieldClass().equals(Double.class)) {
                Double data = (Double) value;
                if (fieldDescriptor.isIndexed())
                    document.add(new DoublePoint(fieldDescriptor.getName(), data));
                if (fieldDescriptor.isStored())
                    document.add(new StoredField(fieldDescriptor.getName(), data));
            } else if (fieldDescriptor.getFieldClass().equals(Byte.class)) {
                Byte data = (Byte) value;
                if (fieldDescriptor.isIndexed())
                    document.add(new IntPoint(fieldDescriptor.getName(), data));
                if (fieldDescriptor.isStored())
                    document.add(new StoredField(fieldDescriptor.getName(), data));
            }
        }
    }

    public static List<Document> getDocuments(Object object, boolean firstLevelOnly) {
        List<Document> documents = new LinkedList<>();
        if (object == null || !isIndexed(object.getClass()))
            return documents;
        Document document = getDocument(object);
        documents.add(document);
        if (!firstLevelOnly) {
            for (Field field : getFields(object.getClass(), IndexedEmbedded.class)) {
                field.setAccessible(true);
                try {
                    Object embedded = field.get(object);
                    documents.addAll(getDocuments(embedded, firstLevelOnly));
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
        return documents;
    }

    public static Iterable<String> getTokens(Analyzer analyzer, String field, String text) {
        List<String> tokens = new LinkedList<>();
        TokenStream stream = analyzer.tokenStream(field, text);
        CharTermAttribute charTermAttribute = stream.addAttribute(CharTermAttribute.class);
        try {
            stream.reset();
            while (true) {

                if (!stream.incrementToken())
                    break;
                String term = charTermAttribute.toString();
                tokens.add(term);
            }
            stream.reset();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return tokens;
    }
}