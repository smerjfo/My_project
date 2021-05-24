let select= function () {
    let selectHeader= document.querySelectorAll
    ('.select_header');
    let  selectItem=document.querySelectorAll(
        '.select_item'
    );
    selectItem.forEach(item=>{
        item.addEventListener('click',selectChoose)
        })


    selectHeader.forEach(item=>{
        item.addEventListener('click',selectToggle
        )
    })
    
    function selectToggle() {
        this.parentElement.classList.toggle
        ('is-active');
    }
    function selectChoose() {
        let text= this.innerText;


        let currentText=this.closest('.select').querySelector(
            '.select_current'
        );
        currentText.innerText=text;
        let lox=this.getAttribute("value");
        let element=this.closest('.select').querySelector('.input');
        element.value=lox;
        console.log(element.value);





    }
}
select()