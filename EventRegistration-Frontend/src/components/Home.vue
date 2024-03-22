<template>
    <div id="home">
        <h1>Event Registration</h1>
        <h2>New In-Person Event</h2>
        <div>
            <input type="text" placeholder="Name" v-model="newEventName" />
            <input type="date" placeholder="Date" v-model="newEventDate" />
            <input type="time" placeholder="Start Time" v-model="newEventStartTime" />
            <input type="time" placeholder="End Time" v-model="newEventEndTime" />
            <input type="text" placeholder="Limit" v-model="newEventRegLimit" />
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
                    <td>{{ e.registrationLimit ? e.registrationLimit : "-" }}</td>
                </tr>
            </tbody>
        </table>
    </div>
</template>

<script>
import axios from "axios";
import config from "../../config";

const frontendUrl = `${config.dev.host}:${config.dev.port}`;
const client = axios.create({
    // IMPORTANT: baseURL, not baseUrl!
    baseURL: config.dev.backendBaseUrl,
    headers: { 'Access-Control-Allow-Origin': frontendUrl }
});

export default {
    name: "Home",
    data() {
        return {
            events: [],
            newEventName: null,
            newEventDate: null,
            newEventStartTime: null,
            newEventEndTime: null,
            newEventRegLimit: null,
            newEventLocation: null
        };
    },
    async created() {
        try {
            const response = await client.get("/events");
            this.events = response.data.events;
        }
        catch (e) {
            // TODO: Show some kind of warning to the user
            console.log(e);
        }
    },
    methods: {
        async createEvent() {
            const newEvent = {
                type: "IN_PERSON",
                name: this.newEventName,
                date: this.newEventDate,
                startTime: this.newEventStartTime,
                endTime: this.newEventEndTime,
                registrationLimit: this.newEventRegLimit,
                location: this.newEventLocation,
            };
            try {
                const response = await client.post("/events", newEvent);
                this.events.push(response.data);
                this.clearInputs();
            }
            catch (e) {
                // TODO: Show some kind of warning to the user
                console.log(e);
            }
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
                || !this.newEventEndTime
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
