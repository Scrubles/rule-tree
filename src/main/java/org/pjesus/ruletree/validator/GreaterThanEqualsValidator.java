package org.pjesus.ruletree.validator;

import org.pjesus.ruletree.rule.Rule;
import org.pjesus.ruletree.selector.DataSelector;

import javax.inject.Inject;
import java.util.Map;

public class GreaterThanEqualsValidator implements Validator {
  private final DataSelector dataSelector;

  @Inject
  public GreaterThanEqualsValidator(DataSelector dataSelector) {
    this.dataSelector = dataSelector;
  }

  @Override
  public Boolean validate(Rule rule, Object data) {
    Map<String, Object> attributes = rule.getAttributes();
    String dataPath = (String) attributes.get("data");
    double value = ((Number) attributes.get("value")).doubleValue();

    try {
      double selectedData = ((Number) this.dataSelector.select(data, dataPath)).doubleValue();
      return selectedData >= value;
    } catch (Exception e) {
      return false;
    }
  }
}
