import {Component, Input, OnInit} from '@angular/core';

@Component({
  selector: 'app-card',
  templateUrl: './card.component.html',
  styleUrls: ['./card.component.css']
})
export class CardComponent implements OnInit {

  constructor() {
    this.color = ""; this.value = "";
  }


  @Input()
  color: string;

  @Input()
  value: string;

  ngOnInit(): void {
  }

  getSrc(){
    return `assets/cards/${this.value.toLowerCase()}_of_${this.color.toLowerCase()}.png`
  }

}
