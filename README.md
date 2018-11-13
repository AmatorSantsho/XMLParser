Java program that filters out rules and writes the result to XML file.


name - The rule name (not unique in input, but should be unique in output)

type - Rule type can be one of 3 values: root, sub and child.
The child rule is the most important; the sub is of average importance and then the root is the least important.

weight - The weight specifies the rule importance within same type.
The greater the weight value is,the more important the rule is. Weight is a positive integer.

The Filtering:
- First, check the rule type. For the same name, the child rule should take precedence over the sub rule and the sub rule over the root rule correspondingly.
- For the same rule name and type, check the rule weight. The rule with greater weight should win.
 
