import { Injectable } from '@angular/core';
import {Observable} from "rxjs";
import {HttpClient} from "@angular/common/http";
import {environment} from "../../../environments/environment";
import {Card} from "../models/card";

@Injectable({
  providedIn: 'root'
})
export class CardService {

  private endPoint = "cards"

  constructor(private http: HttpClient) { }

  public getRandomColorOrder(): Observable<string[]>{
    return this.http.get<string[]>(`${environment.url}/${this.endPoint}/colors/order`)
  }

  public getRandomValueOrder(): Observable<string[]>{
    return this.http.get<string[]>(`${environment.url}/${this.endPoint}/values/order`)
  }

  public getNewHand(size:number): Observable<Card[]>{
    return this.http.get<Card[]>(`${environment.url}/${this.endPoint}/new?size=${size}`)
  }

  public getSortHand(cards: Card[], valueOrder: string[], colorOrder: string[]): Observable<Card[]>{
    return this.http.post<Card[]>(`${environment.url}/${this.endPoint}/sort`, {cards, valueOrder, colorOrder})
  }
}
