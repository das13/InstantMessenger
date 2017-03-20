package ua.sumdu.java.lab2.instant_messenger.message_impl.tests;

import com.tngtech.java.junit.dataprovider.DataProvider;
import com.tngtech.java.junit.dataprovider.DataProviderRunner;
import com.tngtech.java.junit.dataprovider.UseDataProvider;
import org.junit.Test;
import org.junit.runner.RunWith;
import ua.sumdu.java.lab2.instant_messenger.entities.MessageMapImpl;
import ua.sumdu.java.lab2.instant_messenger.parsers.XMLParser;

import java.io.File;
import java.net.UnknownHostException;

import static org.junit.Assert.assertTrue;


@RunWith(DataProviderRunner.class)
public class XMLParserTest {

    @DataProvider
    public static Object[][] data() throws UnknownHostException {
        return JsonParserTest.data();
    }

    @Test
    @UseDataProvider("data")
    public void writeAndReadFile(MessageMapImpl map) throws Exception {
        XMLParser XML_Parser = XMLParser.getInstance();
        File file = File.createTempFile("file","test");
        XML_Parser.write(map, file);
        MessageMapImpl newMap = (MessageMapImpl) XML_Parser.read(file);
        assertTrue(map.equals(newMap));
        file.deleteOnExit();
    }
}