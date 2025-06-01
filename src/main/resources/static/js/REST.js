const API_URL = '/api/items';

document.addEventListener('DOMContentLoaded', () => {
    loadItems();

    document.getElementById('addForm').addEventListener('submit', (e) => {
        e.preventDefault();
        const name = document.getElementById('itemName').value;
        if (name.trim()) {
            addItem(name);
        }
    });
});

function loadItems() {
    fetch(API_URL)
        .then(res => res.json())
        .then(items => {
            const list = document.getElementById('itemList');
            list.innerHTML = '';
            items.forEach(item => {
                const li = document.createElement('li');
                li.setAttribute('data-id', item.id);

                const nameSpan = document.createElement('span');
                nameSpan.textContent = item.name;
                if (item.purchased) {
                    nameSpan.style.textDecoration = 'line-through';
                }

                const doneBtn = document.createElement('button');
                doneBtn.textContent = '✓';
                doneBtn.className = 'done-btn'; // для стилизации
                doneBtn.onclick = () => markPurchased(item.id);

                const deleteBtn = document.createElement('button');
                deleteBtn.textContent = 'Удалить';
                deleteBtn.className = 'delete-btn'; // для стилизации
                deleteBtn.onclick = () => deleteItem(item.id);

                const buttonContainer = document.createElement('span');
                buttonContainer.className = 'button-group';
                buttonContainer.append(doneBtn, deleteBtn);

                li.append(nameSpan, buttonContainer);
                list.appendChild(li);
            });
        });
}

function addItem(name) {
    fetch(API_URL, {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify({ name })
    }).then(() => {
        document.getElementById('itemName').value = '';
        loadItems();
    });
}

function deleteItem(id) {
    fetch(`${API_URL}/${id}`, {
        method: 'DELETE'
    }).then(() => loadItems());
}

function markPurchased(id) {
    fetch(`${API_URL}/${id}/purchase`, {
        method: 'PATCH'
    }).then(() => loadItems()); // Обновит весь список, и зачеркнет то, что пришло с purchased: true
}

