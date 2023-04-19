package utility;

import com.thoughtworks.xstream.converters.Converter;
import com.thoughtworks.xstream.converters.MarshallingContext;
import com.thoughtworks.xstream.converters.UnmarshallingContext;
import com.thoughtworks.xstream.io.HierarchicalStreamReader;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;
import data.Color;
import data.Coordinates;
import data.Location;
import data.Person;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;

public class PersonConverter implements Converter {

    @Override
    public void marshal(Object obj, HierarchicalStreamWriter writer, MarshallingContext context) {
        Person person = (Person) obj;
        writer.startNode("id");
        writer.setValue(String.valueOf(person.getId()));
        writer.endNode();
        writer.startNode("name");
        writer.setValue(person.getName());
        writer.endNode();
        writer.startNode("coordinates");
        context.convertAnother(person.getCoordinates());
        writer.endNode();
        writer.startNode("creationDate");
        writer.setValue(person.getCreationDate().toString());
        writer.endNode();
        writer.startNode("height");
        writer.setValue(String.valueOf(person.getHeight()));
        writer.endNode();
        writer.startNode("birthday");
        writer.setValue(person.getBirthday().toString());
        writer.endNode();
        writer.startNode("weight");
        writer.setValue(String.valueOf(person.getWeight()));
        writer.endNode();
        writer.startNode("hairColor");
        writer.setValue(person.getHairColor().toString());
        writer.endNode();
        writer.startNode("location");
        context.convertAnother(person.getLocation());
        writer.endNode();
    }

    @Override
    public Object unmarshal(HierarchicalStreamReader reader, UnmarshallingContext context) {
        reader.moveDown();
        int id = Integer.parseInt(reader.getValue());
        reader.moveUp();
        reader.moveDown();
        String name = reader.getValue();
        reader.moveUp();
        reader.moveDown();
        Coordinates coordinates = (Coordinates) context.convertAnother(null, Coordinates.class);
        reader.moveUp();
        reader.moveDown();
        LocalDateTime creationDate = LocalDateTime.parse(reader.getValue());
        reader.moveUp();
        reader.moveDown();
        float height = Float.parseFloat(reader.getValue());
        reader.moveUp();
        reader.moveDown();
        ZonedDateTime birthday = ZonedDateTime.parse(reader.getValue());
        reader.moveUp();
        reader.moveDown();
        int weight = Integer.parseInt(reader.getValue());
        reader.moveUp();
        reader.moveDown();
        Color hairColor = Color.valueOf(reader.getValue());
        reader.moveUp();
        reader.moveDown();
        Location location = (Location) context.convertAnother(null, Location.class);
        reader.moveUp();
        return new Person(id, name, coordinates, creationDate, height, birthday, weight, hairColor, location);
    }

    @Override
    public boolean canConvert(Class clazz) {
        return clazz.equals(Person.class);
    }
}
