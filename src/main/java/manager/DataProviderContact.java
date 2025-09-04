package manager;

import models.Contact;
import org.testng.annotations.DataProvider;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class DataProviderContact {

    @DataProvider
    public Iterator<Object[]> example() {
        List<Object[]> list = new ArrayList<>();

        return list.iterator();
    }

    @DataProvider
    public Iterator<Object[]> contactSuccess() {
        List<Object[]> list = new ArrayList<>();
        list.add(new Object[]{Contact.builder()
                .name("Tony")
                .lastName("Molly")
                .phone("343434343654")
                .email("molly@gmail.com")
                .address("Haifa")
                .description("all fields")
                .build()});

        list.add(new Object[]{Contact.builder()
                .name("Lilly")
                .lastName("Molly")
                .phone("956874523654")
                .email("lilly@gmail.com")
                .address("Haifa")
                .build()});

        return list.iterator();
    }

    @DataProvider
    public Iterator<Object[]> contactWrongPhone() {
        List<Object[]> list = new ArrayList<>();
        list.add(new Object[]{Contact.builder()
                .name("Tony")
                .lastName("Molly")
                .phone("123")
                .email("molly123@gmail.com")
                .address("Haifa")
                .description("all fields")
                .build()});
        list.add(new Object[]{Contact.builder()
                .name("Tony")
                .lastName("Molly")
                .phone("12326598742365985412")
                .email("molly987@gmail.com")
                .address("Haifa")
                .description("all fields")
                .build()});
        list.add(new Object[]{Contact.builder()
                .name("Tony")
                .lastName("Molly")
                .phone("wwwwwwwwwwww")
                .email("molly9@gmail.com")
                .address("Haifa")
                .description("all fields")
                .build()});
        list.add(new Object[]{Contact.builder()
                .name("Tony")
                .lastName("Molly")
                .phone("")
                .email("molly99@gmail.com")
                .address("Haifa")
                .description("all fields")
                .build()});
        return list.iterator();
    }

    @DataProvider
    public Iterator<Object[]> contactCSV() throws IOException {
        List<Object[]> list = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new FileReader(new File("src/main/resources/contact.csv")));
        String line = reader.readLine();
        while (line!=null){
            String[] all =  line.split(",");
            list.add(new Object[]{Contact.builder()
                    .name(all[0])
                    .lastName(all[1])
                    .email(all[2])
                    .phone(all[3])
                    .address(all[4])
                    .description(all[5])
                    .build()
            });
            line = reader.readLine();
        }

        return list.iterator();
    }

}