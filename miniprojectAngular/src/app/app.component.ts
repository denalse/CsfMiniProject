import { HttpErrorResponse } from '@angular/common/http';
import { Component } from '@angular/core';
import { ListComponent } from './components/list.component';
import { Contacts } from './contactModel';
import { ContactsService } from './service/contactsService';


@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  //title = 'csfproject';
  componentType: string = 'Add';
  contactsUpdate: boolean = false;
  contactList: Contacts[] = []

  version: number = 0

  constructor(private contactSvc: ContactsService) { }

  processNewContact(newContact: Contacts) {
    console.info('>>>> new contact: ', newContact)
    this.contactSvc.onNewContact(newContact)
      .then(result => {
        console.info('>>>> result: ', result)
      })
      .catch((error: HttpErrorResponse) => {
        console.error('>>>> error: ', error)
        alert(`Error: message=${error.message}, data=${error.error}`)
      })
  }

  updateListComponent(){
    this.contactsUpdate = true;
  }

  toggleShowApp(type: string){
    if(type == 'Add'){
      this.componentType = 'Add';
    }else{
      this.componentType = 'List';
      this.getAllContacts();
    }
  }

  getAllContacts(){
    this.contactSvc.getAllContacts().then(data => {
      this.contactList = JSON.parse(data.data);
      console.info(this.contactList)
    })
}
}
