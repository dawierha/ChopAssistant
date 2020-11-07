package com.justai.jaicf.template.scenario

import com.justai.jaicf.channel.googleactions.actions
import com.justai.jaicf.channel.googleactions.dialogflow.DialogflowIntent
import com.justai.jaicf.channel.googleactions.dialogflow.actionsDialogflow
import com.justai.jaicf.helpers.ssml.break1s
import com.justai.jaicf.model.scenario.Scenario
import com.justai.jaicf.template.jokes.JokeReader
import com.justai.jaicf.template.intent.ChopIntent
import com.justai.jaicf.template.products.ProductSearch
import com.justai.jaicf.template.state.ChopState

object MainScenario: Scenario() {

    init {
        state("main") {
            activators {
                intent(DialogflowIntent.WELCOME)
            }

            action {
                reactions.run{
                    say("Welcome to \$megastore, do you know where to go?")
                    buttons("Yes", "No")
                }
            }

            state(ChopState.NO) {
                activators {
                    intent(ChopIntent.NO)
                }

                action {
                    reactions.go("/"+ChopState.SEARCH)
                }
            }

            state(ChopState.YES){
                activators {
                    intent(ChopIntent.YES)
                }

                action {
                    reactions.say("Okay, here is a special joke I thought about when I saw you.")
                    reactions.go("/" + ChopState.TELLING_JOKE)
                }
            }
        }

        state(ChopState.SEARCH) {
            activators {
                intent(ChopIntent.SEARCH)
            }

            action {
                reactions.run{
                    say("What are you wondering?")
                }
            }
        }

        state(ChopState.PRODUCT) {
            activators {
                intent(ChopIntent.PRODUCT)
            }

            action {
                val slots = activator.actionsDialogflow?.slots ?: mapOf()
                val product: String = slots["product"] as String
                reactions.run {
                    say("""The $product is in the ${ProductSearch.findProductSection(product)} section, $break1s
                        Do you need any more help?""".trimIndent())
                    buttons("Yes", "No")
                }
            }

            state(ChopState.YES) {
                activators {
                    intent(ChopIntent.YES)
                }

                action {
                    reactions.go("/"+ChopState.SEARCH)
                }
            }

            state(ChopState.NO){
                activators {
                    intent(ChopIntent.NO)
                }

                action {
                    reactions.say("Okay, here is a special joke I thought about when I saw you.")
                    reactions.go("/" + ChopState.TELLING_JOKE)
                }
            }

        }

        fallback {
            reactions.say("I don't understand you, but here's a joke, just for you")
            reactions.actions?.run {
                reactions.say(JokeReader.randomJoke())
                endConversation()
            }
        }

        state(ChopState.TELLING_JOKE) {
            activators {
                intent(ChopIntent.JOKE)
            }

            action {
                reactions.actions?.run {
                    reactions.say(JokeReader.randomJoke())
                    endConversation()
                }
            }
        }
    }
}