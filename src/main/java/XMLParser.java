import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;


/**
 * Created by 123 on 14.07.2017.
 */
public class XMLParser {

    private Map<String, Rule> map = new TreeMap<String, Rule>();

    public Map<String, Rule> getMap() {
        return map;
    }

    private static final org.apache.logging.log4j.Logger LOGGER = LogManager.getLogger(XMLParser.class);


    public void parse(InputStream inputStream) {

        try {
            SAXParser saxParser = SAXParserFactory.newInstance().newSAXParser();
            MyHandler myHandler= new MyHandler();
            long start = System.currentTimeMillis();
            saxParser.parse(inputStream, myHandler);
            long end = System.currentTimeMillis();
            LOGGER.info("Parsing is done, time = " + (end - start) + "ms");


        } catch (ParserConfigurationException e) {
            LOGGER.error("ParserConfigurationException"+e);
        } catch (SAXException e) {
            LOGGER.error("ParserException"+e);
        } catch (IOException e) {
            LOGGER.error("IOException"+e);
        }


    }

    public void convertObjectToXml(File file) {
        Rules r = new Rules();


        for (Map.Entry entry : map.entrySet()) {
            String name = (String) entry.getKey();
            Rule currentRule = (Rule) entry.getValue();
            String type = currentRule.getType();
            Integer weight = currentRule.getWeight();
            Rule rule = new Rule();
            rule.setName(name);
            rule.setType(type);
            rule.setWeight(weight);
            List<Rule> list = r.getRules();
            list.add(rule);


        }


        try {
            JAXBContext ctx = JAXBContext.newInstance(Rules.class);
            Marshaller marshaller = ctx.createMarshaller();
            //marshaller.setProperty(Marshaller.JAXB_FRAGMENT,Boolean.TRUE);
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            marshaller.marshal(r, file);


        } catch (JAXBException e) {
            LOGGER.error("Exception by marshalling"+e);
        }


    }


    public class MyHandler extends DefaultHandler {

        private String name;
        private String type;
        private Integer weight;


        @Override
        public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {


            if (qName.equals("rule")) {

                name = attributes.getValue("name");
                type = attributes.getValue("type");
                weight = Integer.parseInt(attributes.getValue("weight"));
                Rule rule = new Rule();
                rule.setName(name);
                rule.setType(type);
                rule.setWeight(weight);
                //if map not contains Rule with that name put Rule in the map
                if (!map.containsKey(name)) {
                    map.put(name, rule);
                } else
                //Gettint Rule with same name from map and compare with current:
                //if priority of current Rule is higher as from map - put current Rule in the map
                {
                    Rule currentRule = map.get(name);
                    if (currentRule.getType().equals(type)) {
                        if (currentRule.getWeight() < weight) {
                            map.put(name, rule);
                        }
                    } else {
                        if (getPriority(currentRule.getType()) < getPriority(type))
                            map.put(name, rule);

                    }
                }


            }
        }

        public Integer getPriority(String type) {
            if (type.equals("root"))
                return 1;
            if (type.equals("sub"))
                return 2;
            if (type.equals("child"))
                return 3;
            return null;
        }

    }
}