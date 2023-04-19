let results = null;
let root = document.getElementById("root");
let search = document.getElementById("search")

// const fileInput = document.getElementById("file-input");

// fileInput.addEventListener("change", (event) => {
//     const file = event.target.files[0]; // get the selected file

//     if (file) {
//         const reader = new FileReader();

//         reader.onload = (event) => {
//             const filePath = fileInput.value; // get the selected file path
//             console.log(filePath);
//         };

//         reader.readAsText(file); // read the contents of the file
//     }
// });

function saveFile() {
	let rows = document.querySelectorAll("input[type='text']");
	let keys = Object.keys(results[0]);
	let data = "";
	for (let i = 0; i < keys.length; i++) {
		data += keys[i] + ",";
	}
	console.log(data);
	data += "\n";
	for (let i = 1; i <= rows.length; i++) {
		data += rows[i - 1].value + ",";

		if ((i % keys.length) + 1 == 0) {
			data += "\n";
		}
	}

	const blob = new Blob([data], { type: "text/csv;charset=utf-8" });
	const url = URL.createObjectURL(blob);
	const link = document.createElement("a");
	link.setAttribute("href", url);
	link.setAttribute("download", "file");
	link.style.visibility = "hidden";
	document.body.appendChild(link);
	link.click();
	document.body.removeChild(link);
}

function read() {
	fetch("http://localhost:8080/ContactWebApplication/showContact")
		.then((response) => response.json())
		.then((data) => {
			results = data["details"];
			console.log(results);
		})
		.catch((e) => {
			console.log("error");
		});
}
DeleteRow = (button) => {
	let input = document.querySelectorAll("." + button.value);
	if (confirm("Do you want to delete the row")) {
		let data = {
			name: input[0].value,
			phoneNo: input[1].value,
			address: input[2].value,
			favState: input[3].value,
		};
		fetch("http://localhost:8080/ContactWebApplication/DeleteContact", {
			method: "POST",
			headers: {
				"Content-Type": "application/json",
			},
			body: JSON.stringify(data),
		})
			.then((response) => response.json())
			.then((data) => {
				let msg = data.msg;
				alert(msg);
				if (msg == "Deleted Successfully") {

					let tr = document.querySelector("." + button.value).parentElement
						.parentElement;
					document.querySelector("tbody").removeChild(tr);
				}
				else {
					read();
					let start = setInterval(() => {
						if (results != null) {
							clearInterval(start);
							appendcontent();
							document
								.querySelectorAll("input[type='text']")
								.forEach((val) => (val.disabled = true));
						}
					}, 50);
				}


			});

		console.log(tr);
	} else {
		return;
	}
};
EditRow = (button) => {
	let input = document.querySelectorAll("." + button.value);

	if (input[0].disabled) {
		input.forEach((val) => {
			val.disabled = false;
			button.innerHTML = `<i class="fa fa-check" aria-hidden="true"></i>`;
		});
	} else {
		let data = {
			name: input[0].value,
			phoneNo: input[1].value,
			address: input[2].value,
			favState: input[3].value,
		};
		fetch("http://localhost:8080/ContactWebApplication/EditServlet", {
			method: "POST",
			headers: {
				"Content-Type": "application/json",
			},
			body: JSON.stringify(data),
		})
			.then((response) => response.json())
			.then((data) => {
				let msg = data.msg;
				alert(msg);
				if (msg == "Updated Successfully") {
					input.forEach((val) => {
						val.disabled = true;
						val.removeAttribute("id");
						button.innerHTML = `<i class="fa fa-pencil" aria-hidden="true"></i>`;
					});
				}
				else {
					read();
					let start = setInterval(() => {
						if (results != null) {
							clearInterval(start);
							appendcontent();
							document
								.querySelectorAll("input[type='text']")
								.forEach((val) => (val.disabled = true));
						}
					}, 50);
				}


			});

	}
};
getRows = (keys) => {
	let res = ``;
	let j = 0;
	for (let i = 0; i < results.length; i++) {
		console.log(i);
		res += `<tr>`;
		while (j < keys.length) {
			res += `<td><input type="text" value="${results[i][keys[j]]
				}" class="r${i}"></td>`;
			j++;
		}
		j = 0;

		res += `<td><button class="btn" value="r${i}" onclick="EditRow(this)"><i class="fa fa-pencil" aria-hidden="true"></i></button><button class="btn" onclick="DeleteRow(this)" value="r${i}"><i class="fa fa-trash" aria-hidden="true"></i></button></td></tr>`;
	}
	return res;
};
AddRow = () => {
	let tbody = document.querySelector("tbody");
	let tr = document.createElement("tr");
	for (let i = 0; i < tbody.children[0].children.length - 1; i++) {
		let td = document.createElement("td");
		let input = document.createElement("input");
		input.setAttribute("type", "text");
		if (i == 0) {
			input.setAttribute("id", "New");
		}

		input.setAttribute("class", `r${tbody.children.length - 1}`);
		td.appendChild(input);
		tr.appendChild(td);
	}
	td = document.createElement("td");
	td.innerHTML = `
    <button class="btn" value="r${tbody.children.length - 1
		}" onclick="EditRow(this)"><i class="fa fa-check" aria-hidden="true"></i></button><button class="btn" onclick="DeleteRow(this)" value="r${tbody.children.length - 1
		}"><i class="fa fa-trash" aria-hidden="true"></i></button>`;
	tr.appendChild(td);
	tbody.appendChild(tr);
};
appendcontent = () => {
	let thead = `<tr>`;
	const keys = Object.keys(results[0]);
	if (!search.value) {
		for (let i = 0; i < keys.length - 2; i++) {
			temp = keys[i];
			keys[i] = keys[i + 1];
			keys[i + 1] = temp;
		}
	}
	//keys = ["name", "phoneNo", "address", "favState"];
	console.log(keys);
	for (let i = 0; i < keys.length; i++) {
		thead += `<th>${keys[i]}</th>`;
	}

	thead += `<th>Action</th></tr>`;
	tbody = getRows(keys);
	root.innerHTML = `
                    <table class="table table-striped table-hover table-bordered">
                    <thead>
                    ${thead}
                    </thead>
                    ${tbody}
                    </table>`;
};
let start = setInterval(() => {
	if (results != null) {
		clearInterval(start);
		appendcontent();
		document
			.querySelectorAll("input[type='text']")
			.forEach((val) => (val.disabled = true));

		search.disabled = false;
	}
}, 50);


function searchContact() {
	let data = {
		name: search.value,
		phoneNo: 0,
		address: "",
		favState: ""
	}
	if (search.value == "") {
		read();
		let start = setInterval(() => {
			if (results != null) {
				clearInterval(start);
				appendcontent();
				document
					.querySelectorAll("input[type='text']")
					.forEach((val) => (val.disabled = true));

				search.disabled = false;
			}
		}, 50);


	} else {
		fetch("http://localhost:8080/ContactWebApplication/SearchServlet", {
			method: "POST",
			headers: {
				"Content-Type": "application/json",
			},
			body: JSON.stringify(data),
		})
			.then(response => response.json()).
			then(data => {
				console.log(data);
				results = data;
				let start = setInterval(() => {
					if (results != null) {
						clearInterval(start);
						appendcontent();
						document
							.querySelectorAll("#root input[type='text']")
							.forEach((val) => (val.disabled = true));

					}
				}, 50);

			}
			)

	}
}
