import { Component, OnInit } from '@angular/core';
import {CardService} from "../../shared/services/card.service";
import {CardColorConstants} from "../../shared/constants/card-color.constants";
import {CardValueConstants} from "../../shared/constants/card-value.constants";
import {Card} from "../../shared/models/card";
import {colors} from "@angular/cli/utilities/color";

@Component({
  selector: 'app-card-hand',
  templateUrl: './card-hand.component.html',
  styleUrls: ['./card-hand.component.css']
})
export class CardHandComponent implements OnInit {

  constructor(private cardService: CardService) {
    this.valueOrder = []; this.colorOrder = []; this.unSortHand = []; this.sortHand = []
  }

  cardColorConstants = CardColorConstants
  cardValueConstants = CardValueConstants

  valueOrder: string[]
  colorOrder: string[]
  unSortHand: Card[]
  sortHand: Card[]

  ngOnInit(): void {
    this.cardService.getRandomColorOrder().subscribe(order => this.colorOrder = order)
    this.cardService.getRandomValueOrder().subscribe(order => this.valueOrder = order)
    this.cardService.getNewHand(10).subscribe(hand => this.unSortHand = hand);
  }

  sort(){
    this.cardService.getSortHand(this.unSortHand, this.valueOrder, this.colorOrder).subscribe(card => this.sortHand = card)
  }

  isValueLoaded(){
    return this.colorOrder.length != 0 && this.valueOrder.length != 0 && this.unSortHand.length != 0;
  }

}
