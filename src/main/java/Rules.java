import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by 123 on 14.07.2017.
 */

@XmlRootElement

public class Rules {
    private List<Rule> ruless = new ArrayList<Rule>();

    public Rules() {
    }

    @XmlElement(name = "rule")
    public List<Rule> getRules() {
        return ruless;
    }

    public void setRules(List<Rule> rule) {
        this.ruless = rule;
    }
}
