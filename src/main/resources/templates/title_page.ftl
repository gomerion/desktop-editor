<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8"/>
    <!-- Подключаем внешний CSS файл -->
    <link rel="stylesheet" type="text/css" href="styles/style.css"/>
</head>
<body>

    <div class="header text-center">
        МИНИСТЕРСТВО НАУКИ И ВЫСШЕГО ОБРАЗОВАНИЯ РОССИЙСКОЙ ФЕДЕРАЦИИ<br/>
        ФЕДЕРАЛЬНОЕ ГОСУДАРСТВЕННОЕ АВТОНОМНОЕ ОБРАЗОВАТЕЛЬНОЕ УЧРЕЖДЕНИЕ ВЫСШЕГО ОБРАЗОВАНИЯ<br/>
        «САМАРСКИЙ НАЦИОНАЛЬНЫЙ ИССЛЕДОВАТЕЛЬСКИЙ УНИВЕРСИТЕТ ИМЕНИ АКАДЕМИКА С. П. КОРОЛЕВА»
    </div>
    <div class="university-sub text-center">
        (Самарский университет)
    </div>

    <div class="department-info text-center">
        <#if institute??>Институт ${institute}<br/></#if>
        <#if faculty??>Факультет ${faculty}<br/></#if>
        Кафедра ${department}
    </div>

    <div class="work-type text-center">
        ${workType!"ВЫПУСКНАЯ КВАЛИФИКАЦИОННАЯ РАБОТА"}
    </div>
    <div class="work-title text-center">
        «${title}»
    </div>

    <div class="specialty-info text-center">
        по ${degreeType!"направлению подготовки"} ${specialtyCode} ${specialtyName}<br/>
        (${qualificationLevel!"уровень бакалавриата"})<br/>
        <#if profileName??>направленность (профиль) «${profileName}»</#if>
    </div>

    <table class="signatures-table">
        <tr>
            <td class="col-role">Студент</td>
            <td class="col-name">${studentName}</td>
        </tr>
        <tr>
            <td class="col-role">Руководитель ВКР,<br/>${supervisorTitle}</td>
            <td class="col-name">${supervisorName}</td>
        </tr>
        <#if consultants??>
            <#list consultants as consultant>
            <tr>
                <td class="col-role">Консультант</td>
                <td class="col-name">${consultant}</td>
            </tr>
            </#list>
        </#if>
        <#if normControllerName??>
        <tr>
            <td class="col-role">Нормоконтролер</td>
            <td class="col-name">${normControllerName}</td>
        </tr>
        </#if>
    </table>

    <div class="footer">
        ${city!"Самара"} ${year}
    </div>

</body>
</html>