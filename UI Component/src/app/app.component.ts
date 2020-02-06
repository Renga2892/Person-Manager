import { Component } from '@angular/core';
import { OnInit } from '@angular/core';
import { PersonService } from 'src/services/person.service';
import { Person } from 'src/model/person';
import { MatDialog, MatDialogRef, MAT_DIALOG_DATA } from '@angular/material';


import { EditPersonComponent } from './EditPerson/edit-person/edit-person.component';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {
  title = 'PersonManager';

  person: Array<Person>;
  newPerson: Person;

  displayedColumns: string[] = ['firstName', 'lastName', 'age', 'favouriteColor', 'hobbies', 'action'];

  constructor(private personService: PersonService, public dialog: MatDialog) { }

  ngOnInit() {

    this.personService.getPerson().subscribe(
      (person) => {
        this.person = person
      });
  }

  openEditDialog(person): void {
    let editPerson = person;
    
    const dialogRef = this.dialog.open(EditPersonComponent, {
      width: '500px',
      data: editPerson
    });

    dialogRef.afterClosed().subscribe(editedPerson => {
      if (editedPerson != undefined) {
        this.personService.savePerson(editedPerson).subscribe(
          (refreshPerson) => {
            //
          });
      }
    });
  }

  openAddDialog(person): void {
    let editPerson = this.newPerson;
    const dialogRef = this.dialog.open(EditPersonComponent, {
      width: '500px',
      data: new Person()
    });

    dialogRef.afterClosed().subscribe(addeddPerson => {
      if (addeddPerson != undefined && this.validate(addeddPerson)) {
        this.personService.addPerson(addeddPerson).subscribe(
          (refreshPerson) => {
            //
          });
      }
    });
  }

  validate(newPer :Person) :Boolean {

    if(newPer.firstName !='' && newPer.lastName !='' && newPer.favouriteColor != '' && newPer.hobbies.length > 0 && newPer.age >0)
      return true;
    else 
      return false;

  }

  deletePerson(personId): void{
    this.personService.deletePerson(personId).subscribe(
      (res) =>{
        alert("Person details deleted successfully");
      }
    )
  }
}