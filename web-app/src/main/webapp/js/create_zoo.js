/**
 * Created by cube on 28.12.2017.
 */
function createZoo() {
    $.ajax({
        type: "POST",
        url: "/createzoo",
        dataType: "json",
    });
}