# Chop Assistant
Personalized shop assistant which helps guiding you efficiently through a store, to find the item you're looking for.

# Description
A voice assistant that allows for customers to find where you have your goods stocked without having to ask any staff. By using Just-AIs powerful conversational framework JAICF we have developed a Google Assistant action that your customers can use hassle-free. The agent is written in kotlin and powered by google's own powerful NLU framework Dialog Flow. The implementation will not only help small business owners with personnel costs, but also customers won't have to unnecessarily interact with people outside of their home when they are asking for help. They will always have the solution with them.

All you have to do as a business owner is to add your store location and layout to the action we have created and you are good to go. We believe that businesses are overlooked in the pandemic and don't get the support they deserve. So we hope to help them as much as humanly possible, primarily by cutting their personnel costs in favor of AI which we will provide for a lot less than what it costs them today.

## How to use

### Run locally

To run this project locally just create a new project from source codes in IntelliJ IDEA and run `Server.kt`.
This will start the server on port 8080. 

#### Obtain global URL

You can then propagate your local instance to the Internet using [ngrok](https://ngrok.com/).
And then run `ngrok http 8080` in the terminal to obtain public URL.

Copy this URL then and use it as a fulfillment URL in your [Dialogflow](https://dialogflow.com) agent (see below).

