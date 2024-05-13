import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { RessourceService } from 'src/app/service/ressourceService';


@Component({
  selector: 'app-ajouter-ressource',
  templateUrl: './ajouter-ressource.component.html',
  styleUrls: ['./ajouter-ressource.component.scss']
})


export class AjouteRessourceComponent  {

  newRessource: any = {};

  constructor(private router: Router, private toastr: ToastrService, private ressourceService: RessourceService) {}


  
  onSubmit() {
    this.ressourceService.addRessourcewithIdUser(this.newRessource)
    .subscribe(
        (response: any) => {
          this.newRessource = {};
          this.router.navigate(['ressource']);
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
