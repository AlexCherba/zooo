/**
 * Created by cube on 28.12.2017.
 */
var doc = document;
var elem = doc.createElement("p");
var content = doc.createTextNode("Existing Zoos:");
elem.appendChild(content);

var reP = doc.getElementById("aboutZoos");
reP.appendChild(content);

function createZoo() {
    $.ajax({
        type: "POST",
        url: "/createzoo",
        dataType: "json",
    });
}