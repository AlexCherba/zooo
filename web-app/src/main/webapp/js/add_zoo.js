/**
 * Created by cube on 28.12.2017.
 */
var tbObject;
var doc = document;
var elem = doc.createElement("p");
var content = doc.createTextNode("Existing Zoos:");
elem.appendChild(content);

addNewZoo();

function viewTableZoo(tbObject) {
    var table = doc.getElementById("tb_zoo");
    var length =  table.rows.length;
        for (var i = 1; i < length; i++) {
            table.deleteRow(1);
        }
    var newRow;
    for (var row in tbObject) {
        newRow = table.insertRow();
        var i = 0;
        for(var key in tbObject[row])(
            newRow.insertCell(i++).innerHTML = tbObject[row][key]
        )
    }
}

function addNewZoo() {
    var nameZoo = doc.getElementById("nameZoo").value;
    var locationZoo = doc.getElementById("locationZoo").value;
    var reqData = {"nameZoo": nameZoo,"locationZoo": locationZoo};

    $.ajax({
        type: "POST",
        url: "/add_zoo",
        dataType: "json",
        //data: JSON.stringify(reqData),
        data: reqData,
        success: function (resp) {
            tbObject = JSON.parse(resp);
            console.log("successful", tbObject);
            viewTableZoo(tbObject);
        },
        error:function(resp,status,err) {
            alert("error: "+resp+" status: "+status+" er:"+err);
        }
    });
}