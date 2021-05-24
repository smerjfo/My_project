th:inline="javascript"
function option(){
    var bills=new Object([[${list}]]);
    var bill=bills[document.getElementById('option1').selectedIndex];

    document.getElementById('flex').value=bill.key;
}
