<template>
    <div id="events">
        <h1>Event Registration</h1>
        <h2>Schedule New In-Person Event</h2>
        <div>
            <input type="text" placeholder="Name" id="name-input" />
            <input type="date" placeholder="Date" id="date-input" />
            <input type="time" placeholder="Start Time" />
            <input type="time" placeholder="End Time" />
            <input type="text" placeholder="Registration Limit" id="reg-limit-input" />
            <input type="text" placeholder="Location" />
            <button id="create-btn">Create Event</button>
            <button class="danger-btn">Clear</button>
            <button class="danger-btn">Delete All Events</button>
        </div>
        <h2>Events</h2>
        <table>
            <tbody id="events-tbody">
                <tr>
                    <th>Name</th>
                    <th>Date</th>
                    <th>Limit</th>
                </tr>
                <tr>
                    <td>Party</td>
                    <td>2024-03-23</td>
                    <td>1</td>
                </tr>
            </tbody>
        </table>
    </div>
</template>

<script></script>

<style>
#events {
    display: flex;
    flex-direction: column;
    align-items: stretch;
}

h2 {
    padding-top: 1em;
    text-decoration: underline;
}

td,
th {
    padding: 0.5em;
    border: 1px solid black;
}

.danger-btn {
    border: 1px solid red;
    color: red;
}

#create-btn {
    border: 1px solid green;
    color: green;
}
</style>