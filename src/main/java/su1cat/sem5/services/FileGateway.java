package su1cat.sem5.services;

import org.springframework.integration.annotation.MessagingGateway;
import org.springframework.integration.file.FileHeaders;
import org.springframework.messaging.handler.annotation.Header;

@MessagingGateway(defaultRequestChannel = "fileWriterChannel")
public interface FileGateway {
    void writeToFile(@Header(FileHeaders.FILENAME) String fileName, String data);
}
