import { ChangeDetectorRef, Component, EventEmitter, Injectable, InjectionToken, Input, OnChanges, OnInit } from '@angular/core';
import { FormArray, FormGroup } from '@angular/forms';
import { Subscription } from 'rxjs';
import { Contacts } from '../contactModel';
import { ContactsService } from '../service/contactsService';

@Component({
  selector: 'app-list',
  templateUrl: './list.component.html',
  styleUrls: ['./list.component.css']
})
@Injectable()
export class ListComponent implements OnInit {
  
  @Input() 
  updatedContacts:Contacts[]= [];
  
  form!: FormGroup
  lineItems!: FormArray
  contactList: Contacts[] = [];

  constructor(private contactSvc: ContactsService) { }

  ngOnInit(): void {
    console.log("HIT")
    console.log(this.updatedContacts);
    this.contactList = this.updatedContacts;
  }

  removeItem(i: number) {
    this.lineItems.removeAt(i)
  }

  // ngOnChanges(){
  //   setTimeout(()=>{
  //     this.getAllContacts();
  //   }, 5000)
  // }

  // ngAfterContentChecked(){
  //   this.cdRef.detectChanges();
  // }

  // getAllContacts(){
  //     this.contactSvc.getAllContacts().then(data => {
  //       this.contactList = JSON.parse(data.data);
  //       console.info(this.contactList)
  //     })
  // }
}
