
"use strict";
 
class Dice {
    
    constructor() {
       this.value = 0;
    }
    
    roll() {
       return (value = Math.floor((Math.random()*6)+1));
    }
}