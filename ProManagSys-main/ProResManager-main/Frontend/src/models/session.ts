export enum TypeSession {
    Active = 'Active',
    Inactive = 'Inactive',
    Scheduled = 'Scheduled'
}

export class session{
    idsession: Number;
    dateDebutSession: Date;
    dateFinSession: Date;
    typeSession:TypeSession;
    moisA: String;
    moisB: String;
    moisC: String;
    joursTravailMoisA: Number;
    joursTravailMoisB: Number;
    joursTravailMoisC: Number;
}