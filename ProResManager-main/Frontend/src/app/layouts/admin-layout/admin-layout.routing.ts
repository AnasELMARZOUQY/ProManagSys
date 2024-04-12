import { Routes } from '@angular/router';
import { UserProfileComponent } from '../../pages/user-profile/user-profile.component';
import { TablesComponent } from '../../pages/tables/tables.component';
import { ProjetComponent } from '../../pages/projet/projet.component';
import { RessourceComponent } from '../../pages/ressource/ressource.component';
import { AjouteRessourceComponent } from '../../pages/ajouter-ressource/ajouter-ressource.component';
import { AjouterAffectationComponent } from 'src/app/pages/ajouter-affectation/ajouter-affectation.component';
import { AjouterProjetComponent } from 'src/app/pages/ajouter-projet/ajouter-projet.component';
import { EditAffectationComponent } from 'src/app/pages/edit-affectation/edit-affectation.component';
import { EditProjetComponent } from 'src/app/pages/edit-projet/edit-projet.component';
import { EditRessourceComponent } from 'src/app/pages/edit-ressource/edit-ressource.component';
import { SessionComponent } from 'src/app/pages/session/session.component';
import { AddSessionComponent } from 'src/app/pages/add-session/add-session.component';
import { EditSessionComponent } from 'src/app/pages/edit-session/edit-session.component';
import { EditUserComponent } from 'src/app/pages/edit-user-profile/edit-user.component';
import { UsersListComponent } from 'src/app/pages/users-list/users-list.component';
export const AdminLayoutRoutes: Routes = [
    { path: 'user-profile',   component: UserProfileComponent },
    { path: 'tables',         component: TablesComponent },
    { path: 'projet',         component: ProjetComponent },
    { path: 'ressource',      component: RessourceComponent },
    { path: 'addresource',    component: AjouteRessourceComponent },
    { path: 'addproject',     component: AjouterProjetComponent },
    { path: 'addaffectation', component: AjouterAffectationComponent },
    { path: 'editprojet/:idprojet', component: EditProjetComponent },
    { path: 'editressource/:idRessource', component: EditRessourceComponent },
    { path: 'editaffectation/:idAffectation', component: EditAffectationComponent },
    { path: 'session', component: SessionComponent },
    { path: 'addsession', component: AddSessionComponent},
    { path: 'editsession/:idsession', component: EditSessionComponent},
    { path: 'edit-user-profile/:iduser', component: EditUserComponent },
    { path: 'userslist', component: UsersListComponent }

];
