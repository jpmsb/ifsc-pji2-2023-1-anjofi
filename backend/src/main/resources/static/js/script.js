const container = document.querySelector('.animation')
const animated = document.querySelector('#animated')
let lastX = 0

container.addEventListener("mouseenter", function (event) {
    let razao = 0.2
    var x = event.pageX * razao
    animated.setAttribute('style', 'transform: translate('+ x +'px, 0) scale(0.8); transition-duration: 500ms;')
    setTimeout(function(){
        animated.removeAttribute('style')
    },181)
    lastX = x
}, false);

container.addEventListener("mousemove", function (event) {
    let razao = 0.2
    var x = event.pageX * razao
    if (x > lastX) {
        animated.style.transform = 'translate('+ x +'px, 0) scale(0.8) scaleX(-1)'
    } else if (x < lastX) {
        animated.style.transform = 'translate('+ -x +'px, 0) scale(0.8) scaleX(-1)'
    }
    lastX = x
}, false);

container.addEventListener("mouseleave", function(event){
    animated.setAttribute('style', 'transform: translate(0px, 0px); transition-duration: 1000ms;')
}, false);
