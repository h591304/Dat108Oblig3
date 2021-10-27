
"use strict";
 
class DiceController {
       
    constructor(root) {

        this.root = root;
       this.run = this.run.bind(this);
       this.rollDice = this.rollDice.bind(this);
       
    }
    
    run() {

        const btRef = document
        .getElementById(this.root)
        .querySelector("*[data-dicebutton]");
        /*const btRef=document.getElementById(this.root).getElementsByTagName("button")[0];*/
        btRef.addEventListener("click",this.rollDice,true);
       
    } 
    
    rollDice() {
        const d = new Dice();
        
        const res = document
        .getElementById(this.root)
        .querySelector("*[data-diceoutput]");

        res.innerHTML = d.roll();
    }
    
}

const controller = new DiceController("root");
document.addEventListener("DOMContentLoaded",controller.run,true);