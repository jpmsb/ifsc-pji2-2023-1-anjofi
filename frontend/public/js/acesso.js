
function cadastrar(){

    
    var nomescad = document.getElementById('nomescad').value
    var senhacad = document.getElementById('senhacad').value

console.log(JSON.stringify({
    nomescad:nomescad,
    senhacad:senhacad
}));

    fetch("/cadastro_usuario",{
        method:'POST',
        body: JSON.stringify({
            nomescad:nomescad,
            senhacad:senhacad
        }) , 
        headers: { "Content-Type" : "application/json" }
        
    })

    .then(async (resp) => {
        console.log('deu certo')
        
    });

}

function logar(){
    var serialcad = document.getElementById('serialcad').value
   
console.log(JSON.stringify({
    serialcad:serialcad,

}));

    fetch("/login_serial",{
        method:'POST',
        body: JSON.stringify({
            serialcad:serialcad,
      
        }) , 
        headers: { "Content-Type" : "application/json" }
        
    })

    .then(async (resp) => {
        var status = await resp.text();
        console.log(status)
        if(status == 'conectado' ){
            location.href = '/acesso-restrito/teste.html'
        }else {
            alert('nome e senha invalidos!!')
        }
        
    });

}
