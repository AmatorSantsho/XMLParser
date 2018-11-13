##Java program that filters out rules and writes the result to XML file.

Sample Input XML file:

\<rules>
\<rule name=ФaФ type=ФchildФ weight=Ф17Ф/>
\<rule name=ФaФ type=ФrootФ weight=Ф29Ф/>
\<rule name=ФbФ type=ФsubФ weight=Ф56Ф/>
\<rule name=ФcФ type=ФchildФ weight=Ф99Ф/>
\<rule name=ФaФ type=ФsubФ weight=Ф12Ф/>
\<rule name=ФcФ type=ФsubФ weight=Ф99Ф/>
\<rule name=ФcФ type=ФrootФ weight=Ф99Ф/>
\<rule name=ФaФ type=ФchildФ weight=Ф34Ф/>
\<rule name=ФdФ type=ФrootФ weight=Ф45Ф/>
\<rule name=ФbФ type=ФsubФ weight=Ф34Ф/>
\</rules>*

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
 \<rule name=ФaФ type=ФchildФ weight=Ф34Ф/>
 \<rule name=ФbФ type=ФsubФ weight=Ф56Ф/>
 \<rule name=ФcФ type=ФchildФ weight=Ф99Ф/>
 \<rule name=ФdФ type=ФrootФ weight=Ф45Ф/>
 \</rules>



##  »нструкци€ по запуску:
         -  —качать проект
         -  —обрать файл jar
         -  ƒл€ запуска jar необходимо передать ему файл с исходными данными как параметр
            например: java -jar XMLParser-1.0-SNAPSHOT.jar source.xml
         -  ѕосле запроса укзать путь и им€ файла дл€ вывода результатов
            например: d:\result.xml