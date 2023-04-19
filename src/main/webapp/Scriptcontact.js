/**
 * 
 */
function login() {
	
	let username = document.getElementById("form3Example3").value;
	let password = document.getElementById("form3Example4").value;
	console.log(username);
	console.log(password);
	//	console.log(username,password)
	// console.log("working");
	let xml = new XMLHttpRequest();

	xml.onreadystatechange = function() {
		console.log(this.response)
		if(xml.readyState == 4 && this.status == 200 && this.response!="true"){
			alert(this.response);
		}
		if (xml.readyState == 4 && this.status == 200 && this.response=="true") {
			location.replace("http://localhost:8080/ContactWebApplication/ContactPage.jsp");
		}
		
	}

	xml.open("post", "contact", true);
	xml.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
	xml.send("username=" + username + "&password=" + password);

}

var searchContacts;
function showContacts(){
	
	let xhr = new XMLHttpRequest();
	xhr.onreadystatechange = function(){
		
		if(xhr.readyState == 4 && this.status == 200){
			
			searchContacts = JSON.parse(this.responseText)["details"];
			let table=document.getElementById("table");
			console.log(table);
			
			for(let i=0;i<searchContacts.length;i++){
				let tr=document.createElement("tr");
				let name=document.createElement("td");
				let icon=document.createElement("td");
				icon.innerHTML=`<i class="fa-solid fa-ellipsis"></i>`;
				name.innerText=searchContacts[i]["name"];
				let phoneno=document.createElement("td");
				phoneno.innerText=searchContacts[i]["phoneNo"];
				
				tr.appendChild(name)
				tr.appendChild(phoneno)
				tr.appendChild(icon)
				table.appendChild(tr);
				console.log(table)
			}
			
		}
		console.log(searchContacts)
	}
	
	xhr.open("post","showContact",true);
	xhr.send();
}