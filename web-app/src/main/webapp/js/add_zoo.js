/**
 * Created by cube on 28.12.2017.
 */
var doc = document;
var elem = doc.createElement("p");
var content = doc.createTextNode("Existing Zoos:");
elem.appendChild(content);

//var reP = doc.getElementById("aboutZoos");
//reP.appendChild(content);

var tbZoos = doc.getElementById("tb_zoo");

//tbZoos.innerHTML("tr");

function addNewZoo() {
    var nameZoo = doc.getElementById("nameZoo").value;
    var locationZoo = doc.getElementById("locationZoo").value;
    var table = doc.getElementById("tb_zoo");
    // or var newRow = table.insertRow();
    var newRow = table.insertRow(table.rows.length);
    var cell0 = newRow.insertCell(0);
    var cell1 = newRow.insertCell(1);
    var cell2 = newRow.insertCell(2);
    cell0.innerHTML = 0;
    cell1.innerHTML = nameZoo;
    cell2.innerHTML = locationZoo;

    $.ajax({
        type: "POST",
        url: "/add_zoo",
        dataType: "json",
        data: {
            nameZoo: nameZoo,
            locationZoo: locationZoo
        },
        success: function (data) {
            var isAddZoo = data.isAddZoo;
        }
    });

    //success: function (data) {
    //    document.getElementById("result").innerText = data.result;
    //}

    /*
    $.ajax({
        type: "POST",
        url: "/createzoo",
        dataType: "json",
    });
*/
}