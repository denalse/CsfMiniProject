import { NgModule } from '@angular/core';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { BrowserModule } from '@angular/platform-browser';
import { HttpClientModule } from '@angular/common/http'

import { AppComponent } from './app.component';
import { AddComponent } from './components/add.component';
import { ListComponent } from './components/list.component';
import { ContactsService } from './service/contactsService';

@NgModule({
  declarations: [
    AppComponent, AddComponent, ListComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    ReactiveFormsModule,
    HttpClientModule
  ],
  providers: [ ContactsService],
  bootstrap: [AppComponent]
})
export class AppModule { }
