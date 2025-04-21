select exists(
  select (1)
    from "user".users u
  where
      u.email = :email or u.cpf = :cpf
);