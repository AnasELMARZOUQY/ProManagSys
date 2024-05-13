import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { ProjetService } from 'src/app/service/projetService';


@Component({
  selector: 'app-ajouter-projet',
  templateUrl: './ajouter-projet.component.html',
  styleUrls: ['./ajouter-projet.component.scss']
})


export class AjouterProjetComponent  {

  newProject: any = {}; 

  constructor(private router: Router, private toastr: ToastrService, private projetService: ProjetService) {}



  onSubmit() {
    this.projetService.addProjetwithIdUser(this.newProject)
    .subscribe(
        (response: any) => {
          this.newProject = {}; 
          this.router.navigate(['projet']);
          const messageFromApi = response.message;
          this.toastr.success(messageFromApi);
        },
        (error: any) => {
          const messageFromApi = error.error.message;
          this.toastr.error(messageFromApi);
        }
    );
  }


}
