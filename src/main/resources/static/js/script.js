async function createTableFromJSON(whatToGet) {
    let response = await fetch('http://localhost:8000/' + whatToGet);
    let jsonData = await response.json();

    let container = $("#container");

    let table = $("<table>");

    let cols = Object.keys(jsonData[0]);

    let thead = $("<thead>");
    let tr = $("<tr>");

    $.each(cols, function (i, item) {
        let th = $("<th>");
        th.text(item);
        tr.append(th);
    });
    thead.append(tr);
    table.append(tr)

    $.each(jsonData, function (i, item) {
        let tr = $("<tr>");
        let vals = Object.values(item);

        $.each(vals, (i, elem) => {
            let td = $("<td>");
            td.text(elem);
            tr.append(td);
        });
        table.append(tr);
    });
    container.append(table)
}