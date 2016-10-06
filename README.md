# dropwizard-vault
Dropwizard bundle to access [vault](http://vaultproject.io) servers and inject secrets into config.

[![Build Status](https://travis-ci.org/3ddysan/dropwizard-vault.svg?branch=master)](https://travis-ci.org/3ddysan/dropwizard-vault)

# Usage
This Library implements a substitutor for the [SubstitutingSourceProvider](http://www.dropwizard.io/1.0.2/docs/manual/core.html#environment-variables), which replaces variables (e.g. ${secret/path@fieldname}) inside the dropwizard config yml with the defined vault secret.

## Configuration
mandatory environment variables:
```
VAULT_ADDR = vault location (e.g. http://localhost:8200/)
VAULT_TOKEN = authentication token
```

optional:
```
VAULT_PREFIXPATH = prefix for secret path
```

## Example
prepare vault secrets:
```shell
vault write secret/path field1="secretFieldValue" value="secretValue"
```
dropwizard config yaml:
```yaml
secrets:
  firstSecretCustomFieldname: "${secret/path@field1}"
  secondSecretWithDefaultFieldname: "${secret/path}"
```
if you set ``VAULT_PREFIXPATH="secret/"``:
```yaml
secrets:
  firstSecretCustomFieldname: "${path@field1}"
  secondSecretWithDefaultFieldname: "${path}"
```
