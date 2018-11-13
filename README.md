Java program that filters out rules and writes the result to XML file.

Sample Input XML file:

\<rules>

\<rule name=”a” type=”child” weight=”17”/>

\<rule name=”a” type=”root” weight=”29”/>

\<rule name=”b” type=”sub” weight=”56”/>

\<rule name=”c” type=”child” weight=”99”/>

\<rule name=”a” type=”sub” weight=”12”/>

\<rule name=”c” type=”sub” weight=”99”/>

\<rule name=”c” type=”root” weight=”99”/>

\<rule name=”a” type=”child” weight=”34”/>

\<rule name=”d” type=”root” weight=”45”/>

\<rule name=”b” type=”sub” weight=”34”/>

\</rules>

name - the rule name (not unique in input, but should be unique in output)

type - rule type can be one of 3 values: root, sub and child.
The child rule is the most important; the sub is of average importance and then the root is the least important.

weight - the weight specifies the rule importance within same type.
The greater the weight value is,the more important the rule is. Weight is a positive integer.

The Filtering:
- First, check the rule type. For the same name, the child rule should take precedence over the sub rule and the sub rule over the root rule correspondingly.
- For the same rule name and type, check the rule weight. The rule with greater weight should win.
 
 Sample output XML file:
 
 \<rules>
 
 \<rule name=”a” type=”child” weight=”34”/>
 
 \<rule name=”b” type=”sub” weight=”56”/>
 
 \<rule name=”c” type=”child” weight=”99”/>
 
 \<rule name=”d” type=”root” weight=”45”/>
 
 \</rules>



##  Инструкция по запуску:
         -  Скачать проект
         -  Собрать файл jar
         -  Для запуска jar необходимо передать ему файл с исходными данными как параметр
            например: java -jar XMLParser-1.0-SNAPSHOT.jar source.xml
         -  После запроса укзать путь и имя файла для вывода результатов
            например: d:\result.xml
