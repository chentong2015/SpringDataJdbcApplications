package example.converter;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

// 使用转换器将属性转换之后存储到数据库中: 实现双向转换的方法
@Converter
public class PersonNameConverter implements AttributeConverter<PersonName, String> {

    private static final String SEPARATOR = ", ";

    @Override
    public String convertToDatabaseColumn(PersonName personName) {
        if (personName == null) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        if (personName.getSurname() != null && !personName.getSurname().isEmpty()) {
            sb.append(personName.getSurname());
            sb.append(SEPARATOR);
        }
        if (personName.getName() != null && !personName.getName().isEmpty()) {
            sb.append(personName.getName());
        }
        return sb.toString();
    }

    @Override
    public PersonName convertToEntityAttribute(String dbPersonName) {
        if (dbPersonName == null || dbPersonName.isEmpty()) {
            return null;
        }
        String[] pieces = dbPersonName.split(SEPARATOR);
        if (pieces.length == 0) {
            return null;
        }

        PersonName personName = new PersonName();
        String firstPiece = !pieces[0].isEmpty() ? pieces[0] : null;
        if (dbPersonName.contains(SEPARATOR)) {
            personName.setSurname(firstPiece);
            if (pieces.length >= 2 && pieces[1] != null && !pieces[1].isEmpty()) {
                personName.setName(pieces[1]);
            }
        } else {
            personName.setName(firstPiece);
        }
        return personName;
    }
}
