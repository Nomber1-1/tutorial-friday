<template>
    <div id="home">
        <h1>Event Registration</h1>
        <h2>New In-Person Event</h2>
        <div>
            <input type="text" placeholder="Name" v-model="newEventName" />
            <input type="date" placeholder="Date" v-model="newEventDate" />
            <input type="time" placeholder="Start Time" v-model="newEventStartTime" />
            <input type="time" placeholder="End Time" v-model="newEventEndTime" />
            <input type="text" placeholder="Limit" v-model="newEventLimit" />
            <input type="text" placeholder="Location" v-model="newEventLocation" />
            <button @click="createEvent()" v-bind:disabled="isCreateBtnDisabled">Create</button>
            <button class="danger-btn" @click="clearInputs()">Clear</button>
        </div>
        <h2>Events</h2>
        <table>
            <tbody>
                <tr>
                    <th>Name</th>
                    <th>Date</th>
                    <th>Limit</th>
                </tr>
                <tr v-for="e in events">
                    <td>{{ e.name }}</td>
                    <td>{{ e.date }}</td>
                    <td>{{ e.limit ? e.limit : "-" }}</td>
                </tr>
            </tbody>
        </table>
    </div>
</template>

<script>
export default {
    name: "Home",
    data() {
        return {
            events: [
                { name: "McGill Juggling", date: "2024-03-22", startTime: "9:00:00", endTime: "21:00:00", limit: 100, location: "McGill" },
                { name: "Solar Eclipse", date: "2024-04-08", startTime: "14:15:00", endTime: "16:30:00", limit: null, location: "Montreal" },
            ],
            newEventName: null,
            newEventDate: null,
            newEventStartTime: null,
            newEventEndTime: null,
            newEventLimit: null,
            newEventLocation: null
        };
    },
    methods: {
        createEvent() {
            const newEvent = {
                name: this.newEventName,
                date: this.newEventDate,
                startTime: this.newEventStartTime,
                endTime: this.newEventEndTime,
                limit: this.newEventLimit,
                location: this.newEventLocation,
            }
            this.events.push(newEvent);
            this.clearInputs();
        },
        clearInputs() {
            this.newEventName = null;
            this.newEventDate = null;
            this.newEventStartTime = null;
            this.newEventEndTime = null;
            this.newEventLimit = null;
            this.newEventLocation = null;
        }
    },
    computed: {
        isCreateBtnDisabled() {
            return (!this.newEventName
                || !this.newEventDate
                || !this.newEventStartTime
                || !this.newEventLocation);
        }
    }
};
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
</style>
