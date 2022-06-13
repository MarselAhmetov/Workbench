package ru.marsel.workbench.props;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "trello")
@Data
public class TrelloProperties {
    String key;
}
