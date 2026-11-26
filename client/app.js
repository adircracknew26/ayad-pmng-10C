const API_URL = "https://adirdomain.duckdns.org/pmng-api/people";

const form = document.getElementById("persona-form");
const nameInput = document.getElementById("name");
const peopleBody = document.getElementById("people-body");
const message = document.getElementById("message");

async function loadPeople() {
    try {
        const response = await fetch(API_URL);

        if (!response.ok) {
            throw new Error("Error " + response.status);
        }

        const people = await response.json();
        renderPeople(people);
    } catch (error) {
        message.textContent = "No se pudo cargar la lista: " + error.message;
    }
}

function renderPeople(people) {
    peopleBody.innerHTML = "";

    if (people.length === 0) {
        const row = document.createElement("tr");
        const cell = document.createElement("td");
        cell.colSpan = 2;
        cell.textContent = "Sin registros";
        row.appendChild(cell);
        peopleBody.appendChild(row);
        return;
    }

    people.forEach(function (person, index) {
        const row = document.createElement("tr");

        const numberCell = document.createElement("td");
        numberCell.textContent = index + 1;

        const nameCell = document.createElement("td");
        nameCell.textContent = person.name;

        row.appendChild(numberCell);
        row.appendChild(nameCell);
        peopleBody.appendChild(row);
    });
}

form.addEventListener("submit", async function (event) {
    event.preventDefault();

    const name = nameInput.value.trim();

    if (name === "") {
        message.textContent = "Escribe un nombre";
        return;
    }

    try {
        const response = await fetch(API_URL, {
            method: "POST",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify({ name: name })
        });

        if (!response.ok) {
            throw new Error("Error " + response.status);
        }

        nameInput.value = "";
        message.textContent = "Nombre guardado";

        await loadPeople();
    } catch (error) {
        message.textContent = "No se pudo guardar: " + error.message;
    }
});

loadPeople();
