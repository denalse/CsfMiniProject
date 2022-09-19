import { Component, EventEmitter, OnInit, Output } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Subject } from 'rxjs';
import { Contacts } from '../contactModel';
import { ContactsService } from '../service/contactsService';
import { ListComponent } from './list.component';

@Component({
  selector: 'app-add',
  templateUrl: './add.component.html',
  styleUrls: ['./add.component.css'],
  providers: [ContactsService]
})
export class AddComponent implements OnInit {

  form!: FormGroup

  @Output()
  onNewContact = new Subject<Contacts>();

  @Output()
  contactListUpdated = new EventEmitter<any>();

  constructor(private fb: FormBuilder) { }

  ngOnInit(): void {
    // @ts-ignore
    this.form = this.createContact()
  }

  createContact() {
    return this.fb.group({
      name: this.fb.control<string>('', [ Validators.required ]),
      email: this.fb.control<string>('', [ Validators.required, Validators.email ]),
      mobile: this.fb.control<string>('', [ Validators.required ])
    })
  }

  processContact() {
    console.info("Add Button Clicked")
    console.info(">>>>> contactForm: ", this.form.value)
    const contact: Contacts = this.form.value as Contacts
    this.form = this.createContact()
    this.onNewContact.next(contact);
    this.contactListUpdated.emit(true);
  }

}
