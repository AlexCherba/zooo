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

//Test Data about zoos
var dataTableFromServer = {
    "Харьковский зоопарк": "Харьков",
    "Киевский зоопарк": "Киев",
};

viewTableZoo(dataTableFromServer);

function viewTableZoo(allZoo) {
    var table = doc.getElementById("tb_zoo");
    var newRow;
    var i = 1;
    for (var key in allZoo) {
        //or newRow = table.insertRow(table.rows.length);
        newRow = table.insertRow();
        newRow.insertCell(0).innerHTML = i++;
        newRow.insertCell(1).innerHTML = key;
        newRow.insertCell(2).innerHTML = allZoo[key];
    }
}

function addNewZoo() {
    var nameZoo = doc.getElementById("nameZoo").value;
    var locationZoo = doc.getElementById("locationZoo").value;

    $.ajax({
        type: "POST",
        url: "/add_zoo",
        dataType: "json",
        data: {
            nameZoo: nameZoo,
            locationZoo: locationZoo
        },
        success: function (data) {
             viewTableZoo(data);
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