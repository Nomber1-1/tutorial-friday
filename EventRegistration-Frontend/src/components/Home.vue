<template>
    <div id="home">
        <h1>Event Registration</h1>
        <h2>New In-Person Event</h2>
        <div>
            <input type="text" placeholder="Name" />
            <input type="date" placeholder="Date" />
            <input type="time" placeholder="Start Time" />
            <input type="time" placeholder="End Time" />
            <input type="text" placeholder="Limit" />
            <input type="text" placeholder="Address" />
            <button id="create-btn">Create</button>
            <button class="danger-btn">Clear</button>
        </div>
        <h2>Events</h2>
        <table>
            <tbody>
                <tr>
                    <th>Name</th>
                    <th>Date</th>
                    <th>Limit</th>
                </tr>
                <tr>
                    <td>McGill Juggling Fest</td>
                    <td>2024-03-22</td>
                    <td>100</td>
                </tr>
            </tbody>
        </table>
    </div>
</template>

<script>

</script>

<style>
#home {
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
    border-color: red;
    color: red;
}

#create-btn {
    border-color: forestgreen;
    color: forestgreen;
}
</style>
