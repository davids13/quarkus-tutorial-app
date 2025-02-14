package com.redhat.developers.avro;

import org.apache.avro.Schema;
import org.apache.avro.file.DataFileReader;
import org.apache.avro.file.DataFileWriter;
import org.apache.avro.generic.GenericDatumReader;
import org.apache.avro.generic.GenericDatumWriter;
import org.apache.avro.generic.GenericRecord;
import org.apache.avro.generic.GenericRecordBuilder;
import org.apache.avro.io.DatumReader;
import org.apache.avro.io.DatumWriter;

import java.io.IOException;
import java.nio.file.Path;

public class GenericRecordAvro {

    public static void main(String[] args) {

        String data =
                """
                           {
                               "type": "record",
                                   "namespace": "com.example",
                                   "name": "Customer",
                                   "doc": "Avro Schema for our Customer",
                                   "fields": [
                               { "name": "first_name", "type": "string", "doc": "First Name of Customer" },
                               { "name": "last_name", "type": "string", "doc": "Last Name of Customer" },
                               { "name": "age", "type": "int", "doc": "Age at the time of registration" },
                               { "name": "height", "type": "float", "doc": "Height at the time of registration in cm" },
                               { "name": "weight", "type": "float", "doc": "Weight at the time of registration in kg" },
                               { "name": "automated_email", "type": "boolean", "default": true, "doc": "Field indicating if the user is enrolled in marketing emails" }
                        ]
                           }
                        """;

        // define schema
        Schema.Parser parser = new Schema.Parser();
        Schema schema = parser.parse(data);

        // create generic record
        GenericRecordBuilder builder = new GenericRecordBuilder(schema);
        builder.set("first_name", "John");
        builder.set("last_name", "Doe");
        builder.set("age", 25);
        builder.set("height", 170f);
        builder.set("weight", 80.5f);
        builder.set("automated_email", false);
        GenericRecord genericRecord = builder.build();

        // write generic record to file
        Path path = Path.of("customer-generic.avro");
        DatumWriter<GenericRecord> datumWriter = new GenericDatumWriter<>();
        try (DataFileWriter<GenericRecord> dataFileWriter = new DataFileWriter<>(datumWriter)) {
            dataFileWriter.create(schema, path.toFile());
            dataFileWriter.append(genericRecord);
            System.out.println("Generic record written to file: " + genericRecord);
        } catch (IOException e) {
            System.out.println("Error writing generic record to file");
            throw new RuntimeException(e);
        }

        // read generic record from file
        GenericRecord genericRecordReader;
        DatumReader<GenericRecord> datumReader = new GenericDatumReader<>();
        try (DataFileReader<GenericRecord> dataFileReader = new DataFileReader<>(path.toFile(), datumReader)) {

            // interpret as specific record
            genericRecordReader = dataFileReader.next();
            System.out.println("Generic record read from file: " + genericRecordReader);
            System.out.println("First name: " + genericRecordReader.get("first_name"));
        } catch (IOException e) {
            System.out.println("Error reading generic record from file");
            throw new RuntimeException(e);
        }

    }
}
