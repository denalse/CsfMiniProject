import { Injectable } from "@angular/core";
import { HttpClient, HttpHeaders } from "@angular/common/http";
import { Contacts, Response } from "../contactModel";
import { firstValueFrom, lastValueFrom, Subject, tap } from "rxjs";

const URL = 'http://localhost:8080/api/'

@Injectable()
export class ContactsService {

    newContact = new Subject<Contacts[]>();

    constructor(private http: HttpClient) { }

    onNewContact(contact: Contacts): Promise<Response> {
        console.log("service hit");

        const headers = new HttpHeaders()
        .set('Content-Type', 'application/json')
        .set('Accept', 'application/json')       


        return lastValueFrom (
            this.http.post<Response>(URL + 'addContact', contact, { headers })
                //.pipe()
        )
    }

    getAllContacts(): Promise<any> {
        return firstValueFrom(
          this.http.get<any>(URL + 'listContacts')
            //Rxjs
            .pipe(
              tap(data => {
                console.info(data)
                this.newContact.next(data)
              })
            )
        )
    }
    // onNewContact(contact: Contacts): Observable<Contacts> {
    //     console.log("service hit");
    //     console.log(URL);
    //     console.log(contact);
    //     return this.http.post<Contacts>(URL, contact);
    // }
}
