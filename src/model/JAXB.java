package model;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;

public class JAXB {
    public static <T> void ConvertObjectToXML(File xmlFile,T objectToSave){

        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(objectToSave.getClass());
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

            // output pretty printed
            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

            jaxbMarshaller.marshal(objectToSave, xmlFile); //print to file
            jaxbMarshaller.marshal(objectToSave, System.out); // print to screen later "remove" that

        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }

    public static <T>  T ConvertXMLToObject(File xmlFile,Class<T> classT) throws JAXBException {
        JAXBContext jaxbContext = JAXBContext.newInstance(classT);
        Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
        T t = (T) jaxbUnmarshaller.unmarshal(xmlFile);
        System.out.println(t);
        return t;
    }
}
