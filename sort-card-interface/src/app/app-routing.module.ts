import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {CardHandComponent} from "./modules/card-hand/card-hand.component";

const routes: Routes = [
  {path:"", component: CardHandComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
